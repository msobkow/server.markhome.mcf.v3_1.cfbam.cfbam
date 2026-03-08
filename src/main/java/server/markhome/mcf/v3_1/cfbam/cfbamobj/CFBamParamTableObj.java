// Description: Java 25 Table Object implementation for Param.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamParamTableObj
	implements ICFBamParamTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamParam.CLASS_CODE;
	protected static final int backingClassCode = ICFBamParam.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamParamObj> members;
	private Map<CFLibDbKeyHash256, ICFBamParamObj> allParam;
	private Map< ICFBamParamByUNameIdxKey,
		ICFBamParamObj > indexByUNameIdx;
	private Map< ICFBamParamByServerMethodIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByServerMethodIdx;
	private Map< ICFBamParamByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByDefSchemaIdx;
	private Map< ICFBamParamByServerTypeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByServerTypeIdx;
	private Map< ICFBamParamByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByPrevIdx;
	private Map< ICFBamParamByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByNextIdx;
	private Map< ICFBamParamByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByContPrevIdx;
	private Map< ICFBamParamByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamParamObj > > indexByContNextIdx;
	public static String TABLE_NAME = "Param";
	public static String TABLE_DBNAME = "srvprm";

	public CFBamParamTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
		allParam = null;
		indexByUNameIdx = null;
		indexByServerMethodIdx = null;
		indexByDefSchemaIdx = null;
		indexByServerTypeIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
	}

	public CFBamParamTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
		allParam = null;
		indexByUNameIdx = null;
		indexByServerMethodIdx = null;
		indexByDefSchemaIdx = null;
		indexByServerTypeIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamParamTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamParamTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allParam = null;
		indexByUNameIdx = null;
		indexByServerMethodIdx = null;
		indexByDefSchemaIdx = null;
		indexByServerTypeIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
		ICFBamParamObj cur = null;
		Iterator<ICFBamParamObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamParamObj.
	 */
	@Override
	public ICFBamParamObj newInstance() {
		ICFBamParamObj inst = new CFBamParamObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamParamObj.
	 */
	@Override
	public ICFBamParamEditObj newEditInstance( ICFBamParamObj orig ) {
		ICFBamParamEditObj edit = new CFBamParamEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamParamObj realiseParam( ICFBamParamObj Obj ) {
		ICFBamParamObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamParamObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamParamObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamParamByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
				keyUNameIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByServerMethodIdx != null ) {
				ICFBamParamByServerMethodIdxKey keyServerMethodIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
				keyServerMethodIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapServerMethodIdx = indexByServerMethodIdx.get( keyServerMethodIdx );
				if( mapServerMethodIdx != null ) {
					mapServerMethodIdx.remove( keepObj.getPKey() );
					if( mapServerMethodIdx.size() <= 0 ) {
						indexByServerMethodIdx.remove( keyServerMethodIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamParamByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByServerTypeIdx != null ) {
				ICFBamParamByServerTypeIdxKey keyServerTypeIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
				keyServerTypeIdx.setOptionalTypeId( keepObj.getOptionalTypeId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapServerTypeIdx = indexByServerTypeIdx.get( keyServerTypeIdx );
				if( mapServerTypeIdx != null ) {
					mapServerTypeIdx.remove( keepObj.getPKey() );
					if( mapServerTypeIdx.size() <= 0 ) {
						indexByServerTypeIdx.remove( keyServerTypeIdx );
					}
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamParamByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamParamByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamParamByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.remove( keepObj.getPKey() );
					if( mapContPrevIdx.size() <= 0 ) {
						indexByContPrevIdx.remove( keyContPrevIdx );
					}
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamParamByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
				keyContNextIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.remove( keepObj.getPKey() );
					if( mapContNextIdx.size() <= 0 ) {
						indexByContNextIdx.remove( keyContNextIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamParamByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
				keyUNameIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByServerMethodIdx != null ) {
				ICFBamParamByServerMethodIdxKey keyServerMethodIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
				keyServerMethodIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapServerMethodIdx = indexByServerMethodIdx.get( keyServerMethodIdx );
				if( mapServerMethodIdx != null ) {
					mapServerMethodIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamParamByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByServerTypeIdx != null ) {
				ICFBamParamByServerTypeIdxKey keyServerTypeIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
				keyServerTypeIdx.setOptionalTypeId( keepObj.getOptionalTypeId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapServerTypeIdx = indexByServerTypeIdx.get( keyServerTypeIdx );
				if( mapServerTypeIdx != null ) {
					mapServerTypeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamParamByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamParamByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamParamByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamParamByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
				keyContNextIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allParam != null ) {
				allParam.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allParam != null ) {
				allParam.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamParamByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
				keyUNameIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByServerMethodIdx != null ) {
				ICFBamParamByServerMethodIdxKey keyServerMethodIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
				keyServerMethodIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapServerMethodIdx = indexByServerMethodIdx.get( keyServerMethodIdx );
				if( mapServerMethodIdx != null ) {
					mapServerMethodIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamParamByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByServerTypeIdx != null ) {
				ICFBamParamByServerTypeIdxKey keyServerTypeIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
				keyServerTypeIdx.setOptionalTypeId( keepObj.getOptionalTypeId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapServerTypeIdx = indexByServerTypeIdx.get( keyServerTypeIdx );
				if( mapServerTypeIdx != null ) {
					mapServerTypeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamParamByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamParamByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamParamByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamParamByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
				keyContNextIdx.setRequiredServerMethodId( keepObj.getRequiredServerMethodId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamParamObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamParamObj createParam( ICFBamParamObj Obj ) {
		ICFBamParamObj obj = Obj;
		ICFBamParam rec = obj.getParamRec();
		schema.getCFBamBackingStore().getTableParam().createParam(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		ICFBamParamObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamParamObj readParam( CFLibDbKeyHash256 pkey ) {
		return( readParam( pkey, false ) );
	}

	@Override
	public ICFBamParamObj readParam( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamParamObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamParam readRec = schema.getCFBamBackingStore().getTableParam().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamParamObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamParamObj readCachedParam( CFLibDbKeyHash256 pkey ) {
		ICFBamParamObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeParam( ICFBamParamObj obj )
	{
		final String S_ProcName = "CFBamParamTableObj.reallyDeepDisposeParam() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamParamObj existing = readCachedParam( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamParamByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
		keyUNameIdx.setRequiredServerMethodId( existing.getRequiredServerMethodId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamParamByServerMethodIdxKey keyServerMethodIdx = schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
		keyServerMethodIdx.setRequiredServerMethodId( existing.getRequiredServerMethodId() );

		ICFBamParamByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamParamByServerTypeIdxKey keyServerTypeIdx = schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
		keyServerTypeIdx.setOptionalTypeId( existing.getOptionalTypeId() );

		ICFBamParamByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamParamByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );

		ICFBamParamByContPrevIdxKey keyContPrevIdx = schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
		keyContPrevIdx.setRequiredServerMethodId( existing.getRequiredServerMethodId() );
		keyContPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamParamByContNextIdxKey keyContNextIdx = schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
		keyContNextIdx.setRequiredServerMethodId( existing.getRequiredServerMethodId() );
		keyContNextIdx.setOptionalNextId( existing.getOptionalNextId() );



		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByServerMethodIdx != null ) {
			if( indexByServerMethodIdx.containsKey( keyServerMethodIdx ) ) {
				indexByServerMethodIdx.get( keyServerMethodIdx ).remove( pkey );
				if( indexByServerMethodIdx.get( keyServerMethodIdx ).size() <= 0 ) {
					indexByServerMethodIdx.remove( keyServerMethodIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByServerTypeIdx != null ) {
			if( indexByServerTypeIdx.containsKey( keyServerTypeIdx ) ) {
				indexByServerTypeIdx.get( keyServerTypeIdx ).remove( pkey );
				if( indexByServerTypeIdx.get( keyServerTypeIdx ).size() <= 0 ) {
					indexByServerTypeIdx.remove( keyServerTypeIdx );
				}
			}
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}

		if( indexByContPrevIdx != null ) {
			if( indexByContPrevIdx.containsKey( keyContPrevIdx ) ) {
				indexByContPrevIdx.get( keyContPrevIdx ).remove( pkey );
				if( indexByContPrevIdx.get( keyContPrevIdx ).size() <= 0 ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}
		}

		if( indexByContNextIdx != null ) {
			if( indexByContNextIdx.containsKey( keyContNextIdx ) ) {
				indexByContNextIdx.get( keyContNextIdx ).remove( pkey );
				if( indexByContNextIdx.get( keyContNextIdx ).size() <= 0 ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeParam( CFLibDbKeyHash256 pkey ) {
		ICFBamParamObj obj = readCachedParam( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamParamObj lockParam( CFLibDbKeyHash256 pkey ) {
		ICFBamParamObj locked = null;
		ICFBamParam lockRec = schema.getCFBamBackingStore().getTableParam().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getParamTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamParamObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockParam", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamParamObj> readAllParam() {
		return( readAllParam( false ) );
	}

	@Override
	public List<ICFBamParamObj> readAllParam( boolean forceRead ) {
		final String S_ProcName = "readAllParam";
		if( ( allParam == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> map = new HashMap<CFLibDbKeyHash256,ICFBamParamObj>();
			allParam = map;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readAllDerived( null );
			ICFBamParam rec;
			ICFBamParamObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
			}
		}
		int len = allParam.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = allParam.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readCachedAllParam() {
		final String S_ProcName = "readCachedAllParam";
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( allParam != null ) {
			int len = allParam.size();
			ICFBamParamObj arr[] = new ICFBamParamObj[len];
			Iterator<ICFBamParamObj> valIter = allParam.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamParamObj readParamByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readParamByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamParamObj readParamByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamParamObj obj = readParam( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamParamObj readParamByUNameIdx( CFLibDbKeyHash256 ServerMethodId,
		String Name )
	{
		return( readParamByUNameIdx( ServerMethodId,
			Name,
			false ) );
	}

	@Override
	public ICFBamParamObj readParamByUNameIdx( CFLibDbKeyHash256 ServerMethodId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamParamByUNameIdxKey,
				ICFBamParamObj >();
		}
		ICFBamParamByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setRequiredName( Name );
		ICFBamParamObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamParam rec = schema.getCFBamBackingStore().getTableParam().readDerivedByUNameIdx( null,
				ServerMethodId,
				Name );
			if( rec != null ) {
				obj = schema.getParamTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamParamObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamParamObj> readParamByServerMethodIdx( CFLibDbKeyHash256 ServerMethodId )
	{
		return( readParamByServerMethodIdx( ServerMethodId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByServerMethodIdx( CFLibDbKeyHash256 ServerMethodId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByServerMethodIdx";
		ICFBamParamByServerMethodIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByServerMethodIdx == null ) {
			indexByServerMethodIdx = new HashMap< ICFBamParamByServerMethodIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByServerMethodIdx.containsKey( key ) ) {
			dict = indexByServerMethodIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByServerMethodIdx( null,
				ServerMethodId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByServerMethodIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readParamByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readParamByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByDefSchemaIdx";
		ICFBamParamByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamParamByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readParamByServerTypeIdx( CFLibDbKeyHash256 TypeId )
	{
		return( readParamByServerTypeIdx( TypeId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByServerTypeIdx( CFLibDbKeyHash256 TypeId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByServerTypeIdx";
		ICFBamParamByServerTypeIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
		key.setOptionalTypeId( TypeId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByServerTypeIdx == null ) {
			indexByServerTypeIdx = new HashMap< ICFBamParamByServerTypeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByServerTypeIdx.containsKey( key ) ) {
			dict = indexByServerTypeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByServerTypeIdx( null,
				TypeId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByServerTypeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readParamByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readParamByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByPrevIdx";
		ICFBamParamByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamParamByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readParamByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readParamByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByNextIdx";
		ICFBamParamByNextIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamParamByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByNextIdx( null,
				NextId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readParamByContPrevIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readParamByContPrevIdx( ServerMethodId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByContPrevIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByContPrevIdx";
		ICFBamParamByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamParamByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByContPrevIdx( null,
				ServerMethodId,
				PrevId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamParamObj> readParamByContNextIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 NextId )
	{
		return( readParamByContNextIdx( ServerMethodId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamParamObj> readParamByContNextIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readParamByContNextIdx";
		ICFBamParamByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamParamByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamParamObj>();
			ICFBamParamObj obj;
			ICFBamParam[] recList = schema.getCFBamBackingStore().getTableParam().readDerivedByContNextIdx( null,
				ServerMethodId,
				NextId );
			ICFBamParam rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getParamTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamParamObj realised = (ICFBamParamObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamParamObj arr[] = new ICFBamParamObj[len];
		Iterator<ICFBamParamObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamParamObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamParamObj readCachedParamByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamParamObj obj = null;
		obj = readCachedParam( Id );
		return( obj );
	}

	@Override
	public ICFBamParamObj readCachedParamByUNameIdx( CFLibDbKeyHash256 ServerMethodId,
		String Name )
	{
		ICFBamParamObj obj = null;
		ICFBamParamByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamParamObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByServerMethodIdx( CFLibDbKeyHash256 ServerMethodId )
	{
		final String S_ProcName = "readCachedParamByServerMethodIdx";
		ICFBamParamByServerMethodIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByServerMethodIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByServerMethodIdx.containsKey( key ) ) {
				dict = indexByServerMethodIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedParamByDefSchemaIdx";
		ICFBamParamByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByServerTypeIdx( CFLibDbKeyHash256 TypeId )
	{
		final String S_ProcName = "readCachedParamByServerTypeIdx";
		ICFBamParamByServerTypeIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
		key.setOptionalTypeId( TypeId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByServerTypeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByServerTypeIdx.containsKey( key ) ) {
				dict = indexByServerTypeIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedParamByPrevIdx";
		ICFBamParamByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedParamByNextIdx";
		ICFBamParamByNextIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByContPrevIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedParamByContPrevIdx";
		ICFBamParamByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamParamObj> readCachedParamByContNextIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedParamByContNextIdx";
		ICFBamParamByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamParamObj> arrayList = new ArrayList<ICFBamParamObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamParamObj arr[] = new ICFBamParamObj[len];
				Iterator<ICFBamParamObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamParamObj obj;
			Iterator<ICFBamParamObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamParamObj> cmp = new Comparator<ICFBamParamObj>() {
			@Override
			public int compare( ICFBamParamObj lhs, ICFBamParamObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeParamByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamParamObj obj = readCachedParamByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeParamByUNameIdx( CFLibDbKeyHash256 ServerMethodId,
		String Name )
	{
		ICFBamParamObj obj = readCachedParamByUNameIdx( ServerMethodId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeParamByServerMethodIdx( CFLibDbKeyHash256 ServerMethodId )
	{
		final String S_ProcName = "deepDisposeParamByServerMethodIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByServerMethodIdx( ServerMethodId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeParamByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeParamByDefSchemaIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeParamByServerTypeIdx( CFLibDbKeyHash256 TypeId )
	{
		final String S_ProcName = "deepDisposeParamByServerTypeIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByServerTypeIdx( TypeId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeParamByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeParamByPrevIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeParamByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeParamByNextIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeParamByContPrevIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeParamByContPrevIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByContPrevIdx( ServerMethodId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeParamByContNextIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeParamByContNextIdx";
		ICFBamParamObj obj;
		List<ICFBamParamObj> arrayList = readCachedParamByContNextIdx( ServerMethodId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamParamObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamParamObj updateParam( ICFBamParamObj Obj ) {
		ICFBamParamObj obj = Obj;
		schema.getCFBamBackingStore().getTableParam().updateParam( null,
			Obj.getParamRec() );
		obj = (ICFBamParamObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteParam( ICFBamParamObj Obj ) {
		ICFBamParamObj obj = Obj;
		ICFBamParamObj prev = obj.getOptionalLookupPrev();
		ICFBamParamObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableParam().deleteParam( null,
			obj.getParamRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteParamByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamParamObj obj = readParam(Id);
		if( obj != null ) {
			ICFBamParamEditObj editObj = (ICFBamParamEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamParamEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeParamByIdIdx( Id );
	}

	@Override
	public void deleteParamByUNameIdx( CFLibDbKeyHash256 ServerMethodId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamParamByUNameIdxKey,
				ICFBamParamObj >();
		}
		ICFBamParamByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByUNameIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setRequiredName( Name );
		ICFBamParamObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByUNameIdx( null,
				ServerMethodId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByUNameIdx( null,
				ServerMethodId,
				Name );
		}
		deepDisposeParamByUNameIdx( ServerMethodId,
				Name );
	}

	@Override
	public void deleteParamByServerMethodIdx( CFLibDbKeyHash256 ServerMethodId )
	{
		ICFBamParamByServerMethodIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByServerMethodIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		if( indexByServerMethodIdx == null ) {
			indexByServerMethodIdx = new HashMap< ICFBamParamByServerMethodIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByServerMethodIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByServerMethodIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByServerMethodIdx( null,
				ServerMethodId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByServerMethodIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByServerMethodIdx( null,
				ServerMethodId );
		}
		deepDisposeParamByServerMethodIdx( ServerMethodId );
	}

	@Override
	public void deleteParamByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamParamByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamParamByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeParamByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteParamByServerTypeIdx( CFLibDbKeyHash256 TypeId )
	{
		ICFBamParamByServerTypeIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByServerTypeIdxKey();
		key.setOptionalTypeId( TypeId );
		if( indexByServerTypeIdx == null ) {
			indexByServerTypeIdx = new HashMap< ICFBamParamByServerTypeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByServerTypeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByServerTypeIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByServerTypeIdx( null,
				TypeId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByServerTypeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByServerTypeIdx( null,
				TypeId );
		}
		deepDisposeParamByServerTypeIdx( TypeId );
	}

	@Override
	public void deleteParamByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamParamByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamParamByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByPrevIdx( null,
				PrevId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByPrevIdx( null,
				PrevId );
		}
		deepDisposeParamByPrevIdx( PrevId );
	}

	@Override
	public void deleteParamByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamParamByNextIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamParamByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByNextIdx( null,
				NextId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByNextIdx( null,
				NextId );
		}
		deepDisposeParamByNextIdx( NextId );
	}

	@Override
	public void deleteParamByContPrevIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamParamByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByContPrevIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamParamByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByContPrevIdx( null,
				ServerMethodId,
				PrevId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByContPrevIdx( null,
				ServerMethodId,
				PrevId );
		}
		deepDisposeParamByContPrevIdx( ServerMethodId,
				PrevId );
	}

	@Override
	public void deleteParamByContNextIdx( CFLibDbKeyHash256 ServerMethodId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamParamByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryParam().newByContNextIdxKey();
		key.setRequiredServerMethodId( ServerMethodId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamParamByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamParamObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamParamObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableParam().deleteParamByContNextIdx( null,
				ServerMethodId,
				NextId );
			Iterator<ICFBamParamObj> iter = dict.values().iterator();
			ICFBamParamObj obj;
			List<ICFBamParamObj> toForget = new LinkedList<ICFBamParamObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableParam().deleteParamByContNextIdx( null,
				ServerMethodId,
				NextId );
		}
		deepDisposeParamByContNextIdx( ServerMethodId,
				NextId );
	}

	/**
	 *	Move the CFBamParamObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamParamObj refreshed cache instance.
	 */
	@Override
	public ICFBamParamObj moveUpParam( ICFBamParamObj Obj ) {
		ICFBamParamObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpParam" );
		}
		ICFBamParam rec = schema.getCFBamBackingStore().getTableParam().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getParamTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamParamObj)obj.realise();
			ICFBamParamObj prev = obj.getOptionalLookupPrev( true );
			ICFBamParamObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamParamObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamParamObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamParamObj refreshed cache instance.
	 */
	@Override
	public ICFBamParamObj moveDownParam( ICFBamParamObj Obj ) {
		ICFBamParamObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownParam" );
		}
		ICFBamParam rec = schema.getCFBamBackingStore().getTableParam().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getParamTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamParamObj)obj.realise();
			ICFBamParamObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamParamObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamParamObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}