/*
 * Copyright (c) 2015 Villu Ruusmann
 *
 * This file is part of JPMML-SkLearn
 *
 * JPMML-SkLearn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-SkLearn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-SkLearn.  If not, see <http://www.gnu.org/licenses/>.
 */
package sklearn.tree;

import org.dmg.pmml.DataType;
import org.dmg.pmml.MiningFunctionType;
import org.dmg.pmml.Output;
import org.dmg.pmml.TreeModel;
import org.jpmml.converter.FeatureSchema;
import org.jpmml.converter.ModelUtil;
import sklearn.Classifier;

abstract
public class TreeClassifier extends Classifier implements HasTree {

	public TreeClassifier(String module, String name){
		super(module, name);
	}

	@Override
	public boolean requiresContinuousInput(){
		return false;
	}

	@Override
	public DataType getDataType(){
		return DataType.FLOAT;
	}

	@Override
	public TreeModel encodeModel(FeatureSchema schema){
		Output output = ModelUtil.createProbabilityOutput(schema);

		TreeModel treeModel = TreeModelUtil.encodeTreeModel(this, MiningFunctionType.CLASSIFICATION, schema)
			.setOutput(output);

		return treeModel;
	}

	@Override
	public Tree getTree(){
		return (Tree)get("tree_");
	}
}